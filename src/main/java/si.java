/*
 * Copyright (C) 2010-2014 Mojang AB
 *
 * Parts of this program are marked with "VanillaIRC". These will be referred
 * to as "Modification" from this point on.
 *
 * Modification Copyright (C) 2014 MinecraftOnline
 *
 * The Modification is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Modification is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.minecraftonline.vanillairc.ChatHandler;
import com.minecraftonline.vanillairc.VanillaIRC;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class si {

    public static final File a = new File("banned-players.json");
    public static final File b = new File("banned-ips.json");
    public static final File c = new File("ops.json");
    public static final File d = new File("whitelist.json");
    private static final Logger h = LogManager.getLogger();
    private static final SimpleDateFormat i = new SimpleDateFormat("yyyy-MM-dd \'at\' HH:mm:ss z");
    private final MinecraftServer j;
    public final List e = Lists.newArrayList();
    public final Map f = Maps.newHashMap();
    private final sq k;
    private final ry l;
    private final sk m;
    private final ss n;
    private final Map o;
    private bqn p;
    private boolean q;
    protected int g;
    private int r;
    private aqp s;
    private boolean t;
    private int u;

    public si(MinecraftServer var1) {
        this.k = new sq(a);
        this.l = new ry(b);
        this.m = new sk(c);
        this.n = new ss(d);
        this.o = Maps.newHashMap();
        this.j = var1;
        this.k.a(false);
        this.l.a(false);
        this.g = 8;
    }

    public void a(gp var1, qr var2) {
        GameProfile var3 = var2.bX();
        rt var4 = this.j.aC();
        GameProfile var5 = var4.a(var3.getId());
        String var6 = var5 == null ? var3.getName() : var5.getName();

        var4.a(var3);
        fl var7 = this.a(var2);

        var2.a((aqh) this.j.a(var2.al));
        var2.c.a((qo) var2.o);
        String var8 = "local";

        if (var1.b() != null) {
            var8 = var1.b().toString();
        }

        h.info(var2.d_() + "[" + var8 + "] logged in with entity id " + var2.E() + " at (" + var2.s + ", " + var2.t + ", " + var2.u + ")");
        qo var9 = this.j.a(var2.al);
        bpq var10 = var9.P();
        ds var11 = var9.M();

        this.a(var2, (qr) null, var9);
        re var12 = new re(this.j, var1, var2);

        var12.a((ib) (new jt(var2.E(), var2.c.b(), var10.t(), var9.t.r(), var9.aa(), this.q(), var10.u(), var9.Q().b("reducedDebugInfo"))));
        var12.a((ib) (new jg("MC|Brand", (new hb(Unpooled.buffer())).a(this.c().getServerModName()))));
        var12.a((ib) (new iv(var10.y(), var10.z())));
        var12.a((ib) (new le(var11)));
        var12.a((ib) (new ka(var2.by)));
        var12.a((ib) (new ks(var2.bg.c)));
        var2.z().d();
        var2.z().b(var2);
        this.a((pg) var9.Z(), var2);
        this.j.aE();
        hx var13;

        if (!var2.d_().equalsIgnoreCase(var6)) {
            var13 = new hx("multiplayer.player.joined.renamed", new Object[] { var2.e_(), var6});
        } else {
            var13 = new hx("multiplayer.player.joined", new Object[] { var2.e_()});
        }

        // VanillaIRC start - field "a" blocks class "a"
        try {
            var13.b().a((a) Class.forName("a").getDeclaredField("o").get(null));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        } // VanillaIRC end
        this.a((hm) var13);
        this.c(var2);
        var12.a(var2.s, var2.t, var2.u, var2.y, var2.z);
        this.b(var2, var9);
        if (this.j.Z().length() > 0) {
            var2.a(this.j.Z(), this.j.aa());
        }

        Iterator var14 = var2.bf().iterator();

        while (var14.hasNext()) {
            wl var15 = (wl) var14.next();

            var12.a((ib) (new lo(var2.E(), var15)));
        }

        var2.f_();
        if (var7 != null && var7.b("Riding", 10)) {
            wq var16 = ww.a(var7.m("Riding"), (aqh) var9);

            if (var16 != null) {
                var16.n = true;
                var9.d(var16);
                var2.a(var16);
                var16.n = false;
            }
        }

    }

    protected void a(pg var1, qr var2) {
        HashSet var3 = Sets.newHashSet();
        Iterator var4 = var1.g().iterator();

        while (var4.hasNext()) {
            brb var5 = (brb) var4.next();

            var2.a.a((ib) (new lb(var5, 0)));
        }

        for (int var9 = 0; var9 < 19; ++var9) {
            bra var10 = var1.a(var9);

            if (var10 != null && !var3.contains(var10)) {
                List var6 = var1.d(var10);
                Iterator var7 = var6.iterator();

                while (var7.hasNext()) {
                    ib var8 = (ib) var7.next();

                    var2.a.a(var8);
                }

                var3.add(var10);
            }
        }

    }

    public void a(qo[] var1) {
        this.p = var1[0].O().e();
        var1[0].af().a((bec) (new sj(this)));
    }

    public void a(qr var1, qo var2) {
        qo var3 = var1.t();

        if (var2 != null) {
            var2.t().c(var1);
        }

        var3.t().a(var1);
        var3.b.c((int) var1.s >> 4, (int) var1.u >> 4);
    }

    public int d() {
        return ql.b(this.t());
    }

    public fl a(qr var1) {
        fl var2 = this.j.c[0].P().i();
        fl var3;

        if (var1.d_().equals(this.j.Q()) && var2 != null) {
            var1.f(var2);
            var3 = var2;
            h.debug("loading single player");
        } else {
            var3 = this.p.b(var1);
        }

        return var3;
    }

    protected void b(qr var1) {
        this.p.a(var1);
        tk var2 = (tk) this.o.get(var1.aG());

        if (var2 != null) {
            var2.b();
        }

    }

    public void c(qr var1) {
        this.e.add(var1);
        this.f.put(var1.aG(), var1);
        this.a((ib) (new ke(kg.a, new qr[] { var1})));
        qo var2 = this.j.a(var1.al);

        var2.d(var1);
        this.a(var1, (qo) null);

        for (int var3 = 0; var3 < this.e.size(); ++var3) {
            qr var4 = (qr) this.e.get(var3);

            var1.a.a((ib) (new ke(kg.a, new qr[] { var4})));
        }

    }

    public void d(qr var1) {
        var1.t().t().d(var1);
    }

    public void e(qr var1) {
        var1.b(tt.f);
        this.b(var1);
        qo var2 = var1.t();

        if (var1.m != null) {
            var2.f(var1.m);
            h.debug("removing player mount");
        }

        var2.e(var1);
        var2.t().c(var1);
        this.e.remove(var1);
        this.f.remove(var1.aG());
        this.o.remove(var1.aG());
        this.a((ib) (new ke(kg.e, new qr[] { var1})));
    }

    public String a(SocketAddress var1, GameProfile var2) {
        String var4;

        if (this.k.a(var2)) {
            sr var5 = (sr) this.k.b((Object) var2); // VanillaIRC - cast to Object

            var4 = "You are banned from this server!\nReason: " + var5.d();
            if (var5.c() != null) {
                var4 = var4 + "\nYour ban will be removed on " + i.format(var5.c());
            }

            return var4;
        } else if (!this.e(var2)) {
            return "You are not white-listed on this server!";
        } else if (this.l.a(var1)) {
            rz var3 = this.l.b(var1);

            var4 = "Your IP address is banned from this server!\nReason: " + var3.d();
            if (var3.c() != null) {
                var4 = var4 + "\nYour ban will be removed on " + i.format(var3.c());
            }

            return var4;
        } else {
            return this.e.size() >= this.g ? "The server is full!" : null;
        }
    }

    public qr f(GameProfile var1) {
        UUID var2 = agt.a(var1);
        ArrayList var3 = Lists.newArrayList();

        qr var5;

        for (int var4 = 0; var4 < this.e.size(); ++var4) {
            var5 = (qr) this.e.get(var4);
            if (var5.aG().equals(var2)) {
                var3.add(var5);
            }
        }

        Iterator var6 = var3.iterator();

        while (var6.hasNext()) {
            var5 = (qr) var6.next();
            var5.a.c("You logged in from another location");
        }

        Object var7;

        if (this.j.V()) {
            var7 = new qf(this.j.a(0));
        } else {
            var7 = new qs(this.j.a(0));
        }

        return new qr(this.j, this.j.a(0), var1, (qs) var7);
    }

    public qr a(qr var1, int var2, boolean var3) {
        var1.t().s().b(var1);
        var1.t().s().b((wq) var1);
        var1.t().t().c(var1);
        this.e.remove(var1);
        this.j.a(var1.al).f(var1);
        ds var4 = var1.cb();
        boolean var5 = var1.cc();

        var1.al = var2;
        Object var6;

        if (this.j.V()) {
            var6 = new qf(this.j.a(var1.al));
        } else {
            var6 = new qs(this.j.a(var1.al));
        }

        qr var7 = new qr(this.j, this.j.a(var1.al), var1.bX(), (qs) var6);

        var7.a = var1.a;
        var7.a((agt) var1, var3);
        var7.d(var1.E());
        var7.o(var1);
        qo var8 = this.j.a(var1.al);

        this.a(var7, var1, var8);
        ds var9;

        if (var4 != null) {
            var9 = agt.a(this.j.a(var1.al), var4, var5);
            if (var9 != null) {
                var7.b((double) ((float) var9.n() + 0.5F), (double) ((float) var9.o() + 0.1F), (double) ((float) var9.p() + 0.5F), 0.0F, 0.0F);
                var7.a(var4, var5);
            } else {
                var7.a.a((ib) (new jl(0, 0.0F)));
            }
        }

        var8.b.c((int) var7.s >> 4, (int) var7.u >> 4);

        while (!var8.a((wq) var7, var7.aN()).isEmpty() && var7.t < 256.0D) {
            var7.b(var7.s, var7.t + 1.0D, var7.u);
        }

        var7.a.a((ib) (new km(var7.al, var7.o.aa(), var7.o.P().u(), var7.c.b())));
        var9 = var8.M();
        var7.a.a(var7.s, var7.t, var7.u, var7.y, var7.z);
        var7.a.a((ib) (new le(var9)));
        var7.a.a((ib) (new ky(var7.bB, var7.bA, var7.bz)));
        this.b(var7, var8);
        var8.t().a(var7);
        var8.d(var7);
        this.e.add(var7);
        this.f.put(var7.aG(), var7);
        var7.f_();
        var7.h(var7.bh());
        return var7;
    }

    public void a(qr var1, int var2) {
        int var3 = var1.al;
        qo var4 = this.j.a(var1.al);

        var1.al = var2;
        qo var5 = this.j.a(var1.al);

        var1.a.a((ib) (new km(var1.al, var1.o.aa(), var1.o.P().u(), var1.c.b())));
        var4.f(var1);
        var1.I = false;
        this.a(var1, var3, var4, var5);
        this.a(var1, var4);
        var1.a.a(var1.s, var1.t, var1.u, var1.y, var1.z);
        var1.c.a(var5);
        this.b(var1, var5);
        this.f(var1);
        Iterator var6 = var1.bf().iterator();

        while (var6.hasNext()) {
            wl var7 = (wl) var6.next();

            var1.a.a((ib) (new lo(var1.E(), var7)));
        }

    }

    public void a(wq var1, int var2, qo var3, qo var4) {
        double var5 = var1.s;
        double var7 = var1.u;
        double var9 = 8.0D;
        float var11 = var1.y;

        var3.B.a("moving");
        if (var1.al == -1) {
            var5 = uq.a(var5 / var9, var4.af().b() + 16.0D, var4.af().d() - 16.0D);
            var7 = uq.a(var7 / var9, var4.af().c() + 16.0D, var4.af().e() - 16.0D);
            var1.b(var5, var1.t, var7, var1.y, var1.z);
            if (var1.af()) {
                var3.a(var1, false);
            }
        } else if (var1.al == 0) {
            var5 = uq.a(var5 * var9, var4.af().b() + 16.0D, var4.af().d() - 16.0D);
            var7 = uq.a(var7 * var9, var4.af().c() + 16.0D, var4.af().e() - 16.0D);
            var1.b(var5, var1.t, var7, var1.y, var1.z);
            if (var1.af()) {
                var3.a(var1, false);
            }
        } else {
            ds var12;

            if (var2 == 1) {
                var12 = var4.M();
            } else {
                var12 = var4.m();
            }

            var5 = (double) var12.n();
            var1.t = (double) var12.o();
            var7 = (double) var12.p();
            var1.b(var5, var1.t, var7, 90.0F, 0.0F);
            if (var1.af()) {
                var3.a(var1, false);
            }
        }

        var3.B.b();
        if (var2 != 1) {
            var3.B.a("placing");
            var5 = (double) uq.a((int) var5, -29999872, 29999872);
            var7 = (double) uq.a((int) var7, -29999872, 29999872);
            if (var1.af()) {
                var1.b(var5, var1.t, var7, var1.y, var1.z);
                var4.u().a(var1, var11);
                var4.d(var1);
                var4.a(var1, false);
            }

            var3.B.b();
        }

        var1.a((aqh) var4);
    }

    public void e() {
        if (++this.u > 600) {
            this.a((ib) (new ke(kg.c, this.e)));
            this.u = 0;
        }

    }

    public void a(ib var1) {
        for (int var2 = 0; var2 < this.e.size(); ++var2) {
            ((qr) this.e.get(var2)).a.a(var1);
        }

    }

    public void a(ib var1, int var2) {
        for (int var3 = 0; var3 < this.e.size(); ++var3) {
            qr var4 = (qr) this.e.get(var3);

            if (var4.al == var2) {
                var4.a.a(var1);
            }
        }

    }

    public void a(agt var1, hm var2) {
        brh var3 = var1.bI();

        if (var3 != null) {
            Collection var4 = var3.d();
            Iterator var5 = var4.iterator();

            while (var5.hasNext()) {
                String var6 = (String) var5.next();
                qr var7 = this.a(var6);

                if (var7 != null && var7 != var1) {
                    var7.a(var2);
                }
            }

        }
    }

    public void b(agt var1, hm var2) {
        brh var3 = var1.bI();

        if (var3 == null) {
            this.a(var2);
        } else {
            for (int var4 = 0; var4 < this.e.size(); ++var4) {
                qr var5 = (qr) this.e.get(var4);

                if (var5.bI() != var3) {
                    var5.a(var2);
                }
            }

        }
    }

    public String f() {
        String var1 = "";

        for (int var2 = 0; var2 < this.e.size(); ++var2) {
            if (var2 > 0) {
                var1 = var1 + ", ";
            }

            var1 = var1 + ((qr) this.e.get(var2)).d_();
        }

        return var1;
    }

    public String[] g() {
        String[] var1 = new String[this.e.size()];

        for (int var2 = 0; var2 < this.e.size(); ++var2) {
            var1[var2] = ((qr) this.e.get(var2)).d_();
        }

        return var1;
    }

    public GameProfile[] h() {
        GameProfile[] var1 = new GameProfile[this.e.size()];

        for (int var2 = 0; var2 < this.e.size(); ++var2) {
            var1[var2] = ((qr) this.e.get(var2)).bX();
        }

        return var1;
    }

    public sq i() {
        return this.k;
    }

    public ry j() {
        return this.l;
    }

    public void a(GameProfile var1) {
        this.m.a((sm) (new sl(var1, this.j.o())));
    }

    public void b(GameProfile var1) {
        this.m.c(var1);
    }

    public boolean e(GameProfile var1) {
        return !this.q || this.m.d(var1) || this.n.d(var1);
    }

    public boolean g(GameProfile var1) {
        return this.m.d(var1) || this.j.R() && this.j.c[0].P().v() && this.j.Q().equalsIgnoreCase(var1.getName()) || this.t;
    }

    public qr a(String var1) {
        Iterator var2 = this.e.iterator();

        qr var3;

        do {
            if (!var2.hasNext()) {
                return null;
            }

            var3 = (qr) var2.next();
        } while (!var3.d_().equalsIgnoreCase(var1));

        return var3;
    }

    public void a(double var1, double var3, double var5, double var7, int var9, ib var10) {
        this.a((agt) null, var1, var3, var5, var7, var9, var10);
    }

    public void a(agt var1, double var2, double var4, double var6, double var8, int var10, ib var11) {
        for (int var12 = 0; var12 < this.e.size(); ++var12) {
            qr var13 = (qr) this.e.get(var12);

            if (var13 != var1 && var13.al == var10) {
                double var14 = var2 - var13.s;
                double var16 = var4 - var13.t;
                double var18 = var6 - var13.u;

                if (var14 * var14 + var16 * var16 + var18 * var18 < var8 * var8) {
                    var13.a.a(var11);
                }
            }
        }

    }

    public void k() {
        for (int var1 = 0; var1 < this.e.size(); ++var1) {
            this.b((qr) this.e.get(var1));
        }

    }

    public void d(GameProfile var1) {
        this.n.a((sm) (new st(var1)));
    }

    public void c(GameProfile var1) {
        this.n.c(var1);
    }

    public ss l() {
        return this.n;
    }

    public String[] m() {
        return this.n.a();
    }

    public sk n() {
        return this.m;
    }

    public String[] o() {
        return this.m.a();
    }

    public void a() {}

    public void b(qr var1, qo var2) {
        bee var3 = this.j.c[0].af();

        var1.a.a((ib) (new ko(var3, kq.d)));
        var1.a.a((ib) (new lf(var2.K(), var2.L(), var2.Q().b("doDaylightCycle"))));
        if (var2.S()) {
            var1.a.a((ib) (new jl(1, 0.0F)));
            var1.a.a((ib) (new jl(7, var2.j(1.0F))));
            var1.a.a((ib) (new jl(8, var2.h(1.0F))));
        }

    }

    public void f(qr var1) {
        var1.a(var1.bh);
        var1.q();
        var1.a.a((ib) (new ks(var1.bg.c)));
    }

    public int p() {
        return this.e.size();
    }

    public int q() {
        return this.g;
    }

    public String[] r() {
        return this.j.c[0].O().e().f();
    }

    public boolean s() {
        return this.q;
    }

    public void a(boolean var1) {
        this.q = var1;
    }

    public List b(String var1) {
        ArrayList var2 = Lists.newArrayList();
        Iterator var3 = this.e.iterator();

        while (var3.hasNext()) {
            qr var4 = (qr) var3.next();

            if (var4.v().equals(var1)) {
                var2.add(var4);
            }
        }

        return var2;
    }

    public int t() {
        return this.r;
    }

    public MinecraftServer c() {
        return this.j;
    }

    public fl u() {
        return null;
    }

    private void a(qr var1, qr var2, aqh var3) {
        if (var2 != null) {
            var1.c.a(var2.c.b());
        } else if (this.s != null) {
            var1.c.a(this.s);
        }

        var1.c.b(var3.P().r());
    }

    public void v() {
        for (int var1 = 0; var1 < this.e.size(); ++var1) {
            ((qr) this.e.get(var1)).a.c("Server closed");
        }

    }

    // VanillaIRC start -- redirect for chat injection
    public void a(hm var1, boolean var2) {
        this.sendMessageToPlayers(var1, var2);

        String translated = var1.c();
        ChatHandler ch = VanillaIRC.getHandler();
        if (ch != null) {
            ch.handleGameMessage(translated);
        }
    }

    private void sendMessageToPlayers(hm var1, boolean var2) {
        this.j.a(var1);
        int var3 = var2 ? 1 : 0;

        this.a((ib) (new ix(var1, (byte) var3)));
    }

    public void sendFromJson(String json) {
        this.sendMessageToPlayers(hn.a(json), false);
    } // VanillaIRC end

    public void a(hm var1) {
        this.a(var1, true);
    }

    public tk a(agt var1) {
        UUID var2 = var1.aG();
        tk var3 = var2 == null ? null : (tk) this.o.get(var2);

        if (var3 == null) {
            File var4 = new File(this.j.a(0).O().b(), "stats");
            File var5 = new File(var4, var2.toString() + ".json");

            if (!var5.exists()) {
                File var6 = new File(var4, var1.d_() + ".json");

                if (var6.exists() && var6.isFile()) {
                    var6.renameTo(var5);
                }
            }

            var3 = new tk(this.j, var5);
            var3.a();
            this.o.put(var2, var3);
        }

        return var3;
    }

    public void a(int var1) {
        this.r = var1;
        if (this.j.c != null) {
            qo[] var2 = this.j.c;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                qo var5 = var2[var4];

                if (var5 != null) {
                    var5.t().a(var1);
                }
            }

        }
    }

    public qr a(UUID var1) {
        return (qr) this.f.get(var1);
    }

}
