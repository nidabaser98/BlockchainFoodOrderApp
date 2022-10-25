package dev.nida.blockchainfoodorderapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import dev.nida.blockchainfoodorderapp.databinding.ActivityMainWalletBinding
import dev.pinkroom.walletconnectkit.WalletConnectKit
import dev.pinkroom.walletconnectkit.WalletConnectKitConfig
import kotlinx.coroutines.launch

class MainActivityWallet : AppCompatActivity(R.layout.activity_main_wallet) {

    private lateinit var binding: ActivityMainWalletBinding

    private val config = WalletConnectKitConfig(
        context = this,
        bridgeUrl = "wss://bridge.aktionariat.com:8887",
        appUrl = "walletconnectkit.com",
        appName = "WalletConnectKit",
        appDescription = "WalletConnectKit is toolkit for WalletConnect!"
    )
    private val walletConnectKit by lazy { WalletConnectKit.Builder(config).build() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainWalletBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        //INTENT
        val payment = intent.getStringExtra("key")
        Log.d("payEther","Ethereum Payment Total: $payment")
        binding.totalPaymentTextView.text = "$payment"

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        walletConnectKit.address?.let {
            menuInflater.inflate(R.menu.toolbar_menu, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.disconnectView -> onDisconnectClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        initLoginView()
        initPerformTransactionView()
    }

    private fun initLoginView() = with(binding) {
        walletConnectButton.start(walletConnectKit, ::onConnected, ::onDisconnected)
    }

    private fun onConnected(address: String) = with(binding) {
        walletConnectButton.visibility = View.GONE
        connectedView.visibility = View.VISIBLE
        connectedAddressView.text = getString(R.string.connected_with, address)
        invalidateOptionsMenu()

    }

    private fun onDisconnected() = with(binding) {
        walletConnectButton.visibility = View.VISIBLE
        connectedView.visibility = View.GONE
        invalidateOptionsMenu()
    }

    private fun initPerformTransactionView() = with(binding) {
        performTransactionView.setOnClickListener {

            val toAddress = "0xb1CaE78813395082cEd4CF4df99Be6f5B58331F3" //toAddressView.text.toString()
            val value = totalPaymentTextView.text.toString()   //valueView.text.toString()

            lifecycleScope.launch {
                runCatching { walletConnectKit.performTransaction(toAddress, value) }
                    .onSuccess {


                        showMessage("İŞLEM BAŞARILI !")



                    }
                    .onFailure { showMessage(it.message ?: it.toString()) }
            }
        }
    }

    private fun onDisconnectClicked() {
        walletConnectKit.removeSession()
    }

    private fun showMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
            .setAnchorView(binding.performTransactionView)
            .show()
    }
}